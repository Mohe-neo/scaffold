package com.idiots.scaffold.service.impl;

import com.idiots.scaffold.entity.SysAccount;
import com.idiots.scaffold.exception.IAuthenticationException;
import com.idiots.scaffold.fastmap.ExpireCallback;
import com.idiots.scaffold.fastmap.IFastMap;
import com.idiots.scaffold.model.dto.LInfo;
import com.idiots.scaffold.model.vo.LResult;
import com.idiots.scaffold.service.ISysAccountService;
import com.idiots.scaffold.service.ISysAuthenticationService;
import com.idiots.scaffold.service.ISysMenuService;
import com.idiots.scaffold.service.ISysRoleService;
import com.idiots.scaffold.utils.RandomValidateCodeUtil;
import com.idiots.scaffold.utils.TokenProviderUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.header.CacheControlServerHttpHeadersWriter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author idiots-devil
 * @since 2023-05-29
 */
@Slf4j
@Service
public class SysAuthenticationServiceImpl implements ISysAuthenticationService {

    @Resource
    private ISysAccountService accountService;

    @Resource
    private ISysRoleService roleService;
    @Resource
    private ISysMenuService menuService;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private IFastMap<String, String> fastMap;
    //@Value("${security.login.rsa}")
    private String secure;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getSysAccountByUsername(username);
    }

    private SysAccount getSysAccountByUsername(String username) {
        SysAccount account = accountService.getAccountByUsername(username);
        List<String> roleCodes = roleService.findRoleCodeByAccount(account.getId());
        List<String> resources = menuService.findSysMenuUrlByRoleCode(roleCodes);

        roleCodes = roleCodes.stream().map(rc -> "ROLE_" + rc).collect(Collectors.toList());
        resources.addAll(roleCodes);
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(String.join(",", resources));
        log.info("grantedAuthorities:{}", grantedAuthorities);
        account.setAuthorities(grantedAuthorities);
        return account;
    }

    @Override
    public LResult login(LInfo info) {
        SysAccount account = getSysAccountByUsername(info.getUsername());
        //String password = info.getPassword();
        //RSA rsa = new RSA(secure, null);
        // Base64解码转成字符串类型
        //String decode = new String(Base64.getDecoder().decode(info.getPassword()), StandardCharsets.UTF_8);
        // 解密数据
        //String password = rsa.decryptStr(decode, KeyType.PrivateKey, StandardCharsets.UTF_8);
        if (!passwordEncoder.matches(info.getPassword(), account.getPassword())) {
            throw new IAuthenticationException("用户密码不正确!");
        }
        if (!account.isEnabled() || !account.isAccountNonLocked()) {
            throw new IllegalStateException("账户不可用或已被锁!");
        }
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(account, null, account.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return LResult.builder()
                .token(TokenProviderUtil.token(info.getUsername()))
                .build();
    }

    @Override
    public String logout(HttpServletResponse response) {
        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.flushBuffer();
        } catch (IOException ex) {
            log.info("推出认证失败，原因：{}", ex.getMessage());
            throw new IllegalStateException("请求失败");
        }
        return "success";
    }

    @Override
    public void verify(HttpServletRequest request, HttpServletResponse response, Long id) {
        // 验证码的过期时间
        long expiredTime = 5 * 60 * 1000;
        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", CacheControlServerHttpHeadersWriter.PRAGMA_VALUE);
        response.setDateHeader("Expires", System.currentTimeMillis() + expiredTime);
        response.setHeader("Access-Control-Expose-Headers", "key");
        String uuid = UUID.randomUUID().toString().replace("-", "");
        response.setHeader("key", uuid);
        try {
            RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
            String randomCode = randomValidateCode.getRandomCode(request, response);
            fastMap.put(uuid, randomCode);
            log.info("fast map:{}", fastMap);
            // 设置过期的回调事件
            fastMap.expire(uuid, expiredTime, (ExpireCallback) (key, val) -> fastMap.remove(uuid));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
