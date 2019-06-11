package com.ifam.tccbackend.config;



//@EnableAuthorizationServer
public class AuthorizationServerConfig  { //extends AuthorizationServerConfigurerAdapter
    /*@Autowired
    public AuthenticationManager authenticationManager;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("angular")
                .secret("@ngul@r")
                .scopes("read", "write")
                .authorizedGrantTypes("password")
                .accessTokenValiditySeconds(1800);
    }
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(tokenStore())
                .authenticationManager(authenticationManager);
    }
    @Bean
    public TokenStore tokenStore(){
        return new InMemoryTokenStore();
    }*/

}