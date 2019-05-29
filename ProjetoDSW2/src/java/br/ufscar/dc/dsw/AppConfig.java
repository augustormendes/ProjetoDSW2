    package br.ufscar.dc.dsw;

    import javax.sql.DataSource;
    import org.springframework.jdbc.datasource.DriverManagerDataSource;
    import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
    import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

    @EnableWebSecurity
    public class AppConfig extends WebSecurityConfigurerAdapter {

        private static DataSource dataSource;

        public AppConfig() throws ClassNotFoundException {
            dataSource = AppConfig.getDataSource();
        }

        @Override
        public void configure(AuthenticationManagerBuilder builder)
                throws Exception {


            String userSql = "SELECT email, senha, ativo FROM Usuario "
                    + " WHERE email = ?";
            String roleSql = "select email, nome "
                            + "from Papel where email = ?";


            builder.jdbcAuthentication().dataSource(dataSource)
                    .usersByUsernameQuery(userSql)
                    .authoritiesByUsernameQuery(roleSql)
                    .passwordEncoder(new BCryptPasswordEncoder());
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/faces/site/form.xhtml").hasRole("ADMIN")
                .antMatchers("/faces/teatro/form.xhtml").hasRole("ADMIN")
                .antMatchers("/faces/promocao/form.xhtml").hasRole("TEATRO")
                .and()
                .formLogin()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));

        http.csrf().disable();
    }

        public static DataSource getDataSource() throws ClassNotFoundException {

            if (dataSource == null) {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                String url = "jdbc:derby://localhost:1527/VendaIngressoBD";
                String user = "root";
                String password = "root";
                dataSource = new DriverManagerDataSource(url, user, password);
            }

            return dataSource;
        }
    }
