package com.sftp.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity // Mengaktifkan fitur keamanan web Spring Security
public class SftpSecurityConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/files/delete/**")
                .allowedOrigins("http://localhost:8080") // ganti sesuai asal frontend
                .allowedMethods("DELETE");
    }
    // Konfigurasi Filter Chain Keamanan HTTP
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        		.csrf(csrf -> csrf.ignoringRequestMatchers("/public/**"))	
            .authorizeHttpRequests(authorize -> authorize
                // Izinkan akses tanpa otentikasi ke halaman home dan aset statis
                .requestMatchers("/", "/home", "/css/**", "/js/**", "/images/**", "/files/delete/**", "/files/upload").permitAll()
                // Semua request lainnya memerlukan otentikasi
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                // Tentukan URL halaman login kustom
                .loginPage("/login")
                // Izinkan akses tanpa otentikasi ke halaman login
                .permitAll()
                // Redirect ke /secured setelah login berhasil (opsional)
                .defaultSuccessUrl("/files", true)
            )
            .logout(logout -> logout
                // Izinkan semua pengguna untuk mengakses URL logout
                .permitAll()
                // Redirect ke halaman home setelah logout
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true) // Invalidasi sesi HTTP saat logout
                .deleteCookies("JSESSIONID") // Hapus cookie sesi saat logout
            )
            .sessionManagement(session -> session
                    // Kebijakan pembuatan sesi: IF_REQUIRED akan membuat sesi jika diperlukan (default)
                    .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                    // URL tujuan ketika sesi tidak valid (misalnya, sesi kedaluwarsa secara otomatis)
                    .invalidSessionUrl("/login?expired")
                    // Hanya izinkan 1 sesi aktif per pengguna pada satu waktu
                    .maximumSessions(1)
                    // Jika true, mencegah pengguna baru login jika sudah memiliki sesi aktif.
                    // Jika false (default), sesi lama akan di-invalidate jika pengguna login lagi.
                    .maxSessionsPreventsLogin(false)
                    // URL tujuan ketika sesi pengguna kedaluwarsa karena maxSessions (misalnya, pengguna login dari tempat lain)
                    .expiredUrl("/login?expired")
            );
        return http.build();
    }

    
    
    // Konfigurasi UserDetailsService untuk mengelola informasi pengguna
    // Dalam contoh ini, kita menggunakan pengguna dalam memori (in-memory users)
    // Untuk aplikasi produksi, Anda akan mengintegrasikannya dengan database atau sistem identitas lainnya.
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
            .username("user")
            .password(passwordEncoder().encode("password")) // Encode password
            .roles("USER")
            .build();

        UserDetails admin = User.builder()
            .username("admin")
            .password(passwordEncoder().encode("adminpass")) // Encode password
            .roles("ADMIN", "USER") // Admin memiliki kedua role
            .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    // Bean untuk PasswordEncoder
    // BCryptPasswordEncoder direkomendasikan untuk hashing password secara aman
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
