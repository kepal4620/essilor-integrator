package essilor.integrator.adapter.tools.httpclient;

import org.apache.http.*;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.springframework.beans.factory.FactoryBean;

import javax.annotation.PostConstruct;
import java.io.IOException;

public class EetHttpClientFactoryBean implements FactoryBean<HttpClient> {

    private String proxyHost;
    private int proxyPort;
    private String proxyUser;
    private String proxyPassword;
    private String proxyEnable;
    private HttpClientBuilder builder;

    public EetHttpClientFactoryBean(String proxyHost, int proxyPort, String proxyUser,
                                 String proxyPassword, String proxyEnable) {
        this.proxyHost = proxyHost;
        this.proxyPort = proxyPort;
        this.proxyUser = proxyUser;
        this.proxyPassword = proxyPassword;
        this.proxyEnable = proxyEnable;
    }

    @PostConstruct
    public void init() throws Exception {
        builder = HttpClients.custom();
        if ("true".equalsIgnoreCase(proxyEnable)) {
            CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider
                    .setCredentials(new AuthScope(proxyHost, AuthScope.ANY_PORT), new UsernamePasswordCredentials(
                            proxyUser, proxyPassword));

            builder.setDefaultCredentialsProvider(credentialsProvider);
            builder.setProxy(new HttpHost(
                    proxyHost, proxyPort));
        }
        builder.addInterceptorFirst(new HttpRequestInterceptor() {
            public void process(final HttpRequest request,
                                final HttpContext context) throws HttpException, IOException {
                if (request instanceof HttpEntityEnclosingRequest) {
                    if (request.containsHeader(HTTP.TRANSFER_ENCODING)) {
                        request.removeHeaders(HTTP.TRANSFER_ENCODING);
                    } if (request.containsHeader(HTTP.CONTENT_LEN)) {
                        request.removeHeaders(HTTP.CONTENT_LEN);
                    }
                }
            }
        });

        /*
        SSLContextBuilder sslContextBuilder = new SSLContextBuilder();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslContextBuilder.build(),
                new String[] { "TLSv1" },
                new String[] {
                        "SSL_RSA_WITH_RC4_128_MD5",
                        "SSL_RSA_WITH_RC4_128_SHA",
                        "TLS_RSA_WITH_AES_128_CBC_SHA",
//                        "TLS_RSA_WITH_AES_256_CBC_SHA",
                        "SSL_RSA_WITH_3DES_EDE_CBC_SHA",
                        "SSL_RSA_WITH_DES_CBC_SHA",
                        "SSL_RSA_EXPORT_WITH_RC4_40_MD5",
                        "SSL_RSA_EXPORT_WITH_DES40_CBC_SHA",
                        "TLS_EMPTY_RENEGOTIATION_INFO_SCSV"
                },
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        builder.setSSLSocketFactory(sslsf);
        */

        SSLContextBuilder sslContextBuilder = new SSLContextBuilder();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslContextBuilder.build(),
                new String[] { "TLSv1.2" },
                null,
                SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        builder.setSSLSocketFactory(sslsf);
    }

    public HttpClient getObject() throws Exception {
        return builder.build();
    }

    public Class<?> getObjectType() {
        return HttpClient.class;
    }

    public boolean isSingleton() {
        return false;
    }

}
