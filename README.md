# oauth2-sso-demo
oauth2 实现单点登录


http://client1-web.sso.com/client1Page/#/home

可登录用户有admin/123456，test/123456


C:\Windows\System32\drivers\etc

127.0.0.1       auth.sso.com
127.0.0.1       client1.sso.com
127.0.0.1       client2.sso.com
127.0.0.1       auth-web.sso.com
127.0.0.1       client1-web.sso.com
127.0.0.1       client2-web.sso.com


==========nginx 问题配置==========

#user  nobody;
worker_processes  1;

#error_log  logs/error.log;
#error_log  logs/error.log  notice;
#error_log  logs/error.log  info;

#pid        logs/nginx.pid;


events {
    worker_connections  1024;
}


http {
    include       mime.types;
    default_type  application/octet-stream;

    #log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
    #                  '$status $body_bytes_sent "$http_referer" '
    #                  '"$http_user_agent" "$http_x_forwarded_for"';

    #access_log  logs/access.log  main;

    sendfile        on;
    #tcp_nopush     on;

    #keepalive_timeout  0;
    keepalive_timeout  65;

    #gzip  on;

    server {
		listen       80;
		server_name  client1.sso.com;
		location /client1/ {
			proxy_set_header Host $host;
			proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
			proxy_pass http://localhost:8081/client1/;
		}
    }
	
	server {
		listen       80;
		server_name  client1-web.sso.com;
		location /client1Page/ {
			proxy_set_header Host $host;
			proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
			proxy_pass http://localhost:8084/;
		}
		location ~ .*\.(js|css|ttf|woff|woff2|ico)$ {
			proxy_pass http://localhost:8084;
		}
    }

    server {
		listen       80;
		server_name  client2.sso.com;
		location /client2/ {
			proxy_set_header Host $host;
			proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
			proxy_pass http://localhost:8082/client2/;
		}
    }
	
    server {
		listen       80;
		server_name  client2-web.sso.com;
		location /client2Page/ {
			proxy_set_header Host $host;
			proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
			proxy_pass http://localhost:8085/;
		}

		location ~ .*\.(js|css|ttf|woff|woff2|ico)$ {
			proxy_pass http://localhost:8085;
		}
    }

    server {
        listen       80;
        server_name  oauth.sso.com;
		location /auth/ {
			proxy_set_header Host $host;
			proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
			proxy_pass http://localhost:8080/auth/;
		}
    }

    server {
        listen       80;
        server_name  oauth-web.sso.com;
		location /authPage/ {
			proxy_set_header Host $host;
			proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
			proxy_pass http://localhost:8083/;
		}

		location ~ .*\.(js|css|ttf|woff|woff2|ico)$ {
			proxy_pass http://localhost:8083;
		}
    }

}

==========nginx 问题配置==========
