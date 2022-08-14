package com.hutuxiaogui.autoconfigure.aliyun.oss.config;

import static com.aliyun.oss.ClientConfiguration.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.common.comm.Protocol;
import com.aliyun.oss.common.comm.SignVersion;

/**
 * @author cc
 */
public class AliyunOssClientConfig {
    /**
     * 用户代理，指HTTP的User-Agent头。默认为aliyun-sdk-java。
     */
    private String userAgent = DEFAULT_USER_AGENT;
    /**
     * 请求失败后最大的重试次数。默认3次。
     */
    private int maxErrorRetry = DEFAULT_MAX_RETRIES;
    /**
     * 从连接池中获取连接的超时时间（单位：毫秒）。默认不超时。
     */
    private int connectionRequestTimeout = DEFAULT_CONNECTION_REQUEST_TIMEOUT;
    /**
     * 建立连接的超时时间（单位：毫秒）。默认为50000毫秒。
     */
    private int connectionTimeout = DEFAULT_CONNECTION_TIMEOUT;
    /**
     * Socket层传输数据的超时时间（单位：毫秒）。默认为50000毫秒。
     */
    private int socketTimeout = DEFAULT_SOCKET_TIMEOUT;
    /**
     * 允许打开的最大HTTP连接数。默认为1024
     */
    private int maxConnections = DEFAULT_MAX_CONNECTIONS;
    /**
     * 连接TTL (生存时间)。Http连接由连接管理器用TTL缓存。
     */
    private long connectionTTL = DEFAULT_CONNECTION_TTL;
    /**
     * 是否使用com.aliyun.oss.common.comm.IdleConnectionReaper管理过期连接,默认开启
     */
    private boolean useReaper = DEFAULT_USE_REAPER;
    /**
     * 连接空闲超时时间，超时则关闭连接（单位：毫秒）。默认为60000毫秒。
     */
    private long idleConnectionTime = DEFAULT_IDLE_CONNECTION_TIME;
    /**
     * 连接OSS所采用的协议（HTTP或HTTPS），默认为HTTP。
     */
    private Protocol protocol = Protocol.HTTP;
    /**
     * 代理服务器主机地址。
     */
    private String proxyHost = null;
    /**
     * 代理服务器端口。
     */
    private int proxyPort = -1;
    /**
     * 代理服务器验证的用户名。
     */
    private String proxyUsername = null;
    /**
     * 代理服务器验证的密码。
     */
    private String proxyPassword = null;
    /**
     * 代理服务器的域，该域可以执行NTLM认证
     */
    private String proxyDomain = null;
    /**
     * 代理主机的NTLM身份验证服务器
     */
    private String proxyWorkstation = null;
    /**
     * 是否支持CNAME作为Endpoint，默认支持CNAME。
     */
    private boolean supportCname = false;
    /**
     * 设置不可变排除的CName列表 ---- 任何以该列表中的项目结尾的域都不会进行Cname解析。
     */
    private List<String> cnameExcludeList = new ArrayList<String>();
    /**
     * 是否开启二级域名（Second Level Domain）的访问方式，默认不开启。
     */
    private boolean sldEnabled = false;
    /**
     * 请求超时时间，单位：毫秒。默认情况下是5分钟。
     */
    private int requestTimeout = DEFAULT_REQUEST_TIMEOUT;
    /**
     * 是否启用请求超时校验。默认情况下，它是禁用的。
     */
    private boolean requestTimeoutEnabled = false;
    /**
     * 设置慢请求的延迟阈值。如果请求的延迟大于延迟，则将记录该请求。默认情况下，阈值为5分钟。
     */
    private long slowRequestsThreshold = DEFAULT_SLOW_REQUESTS_THRESHOLD;
    /**
     * 设置默认的http头。所有请求头将自动添加到每个请求中。如果在请求中也指定了相同的请求头，则默认的标头将被覆盖。
     */
    private Map<String, String> defaultHeaders = new LinkedHashMap<String, String>();
    /**
     * 是否在上传和下载时启用CRC校验，默认启用
     */
    private boolean crcCheckEnabled = true;
    /**
     * 所有请求设置签名版本
     */
    private SignVersion signatureVersion = DEFAULT_SIGNATURE_VERSION;
    /**
     * 设置OSS服务端时间和本地时间之间的差异，以毫秒为单位。
     */
    private long tickOffset = 0;
    /**
     * 是否开启HTTP重定向。
     * 说明: Java SDK 3.10.1及以上版本支持设置是否开启HTTP重定向，默认开启。
     */
    private boolean redirectEnable = true;
    /**
     * 是否开启SSL证书校验。
     * 说明: Java SDK 3.10.1及以上版本支持设置是否开启SSL证书校验，默认开启。
     */
    private boolean verifySSLEnable = true;
    /**
     * 是否开启日志记录连接池统计信息
     */
    private boolean logConnectionPoolStats = false;
    /**
     * 是否使用系统属性值
     */
    private boolean useSystemPropertyValues = false;

    public ClientBuilderConfiguration toClientConfig() {
        ClientBuilderConfiguration clientConfig = new ClientBuilderConfiguration();
        BeanUtils.copyProperties(this, clientConfig, "proxyPort", "tickOffset");
        if (this.proxyPort != -1) {
            clientConfig.setProxyPort(this.proxyPort);
        }
        if (this.tickOffset != 0) {
            clientConfig.setTickOffset(this.tickOffset);
        }
        return clientConfig;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public int getMaxErrorRetry() {
        return maxErrorRetry;
    }

    public void setMaxErrorRetry(int maxErrorRetry) {
        this.maxErrorRetry = maxErrorRetry;
    }

    public int getConnectionRequestTimeout() {
        return connectionRequestTimeout;
    }

    public void setConnectionRequestTimeout(int connectionRequestTimeout) {
        this.connectionRequestTimeout = connectionRequestTimeout;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public int getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public int getMaxConnections() {
        return maxConnections;
    }

    public void setMaxConnections(int maxConnections) {
        this.maxConnections = maxConnections;
    }

    public long getConnectionTTL() {
        return connectionTTL;
    }

    public void setConnectionTTL(long connectionTTL) {
        this.connectionTTL = connectionTTL;
    }

    public boolean isUseReaper() {
        return useReaper;
    }

    public void setUseReaper(boolean useReaper) {
        this.useReaper = useReaper;
    }

    public long getIdleConnectionTime() {
        return idleConnectionTime;
    }

    public void setIdleConnectionTime(long idleConnectionTime) {
        this.idleConnectionTime = idleConnectionTime;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

    public String getProxyHost() {
        return proxyHost;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public int getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(int proxyPort) {
        this.proxyPort = proxyPort;
    }

    public String getProxyUsername() {
        return proxyUsername;
    }

    public void setProxyUsername(String proxyUsername) {
        this.proxyUsername = proxyUsername;
    }

    public String getProxyPassword() {
        return proxyPassword;
    }

    public void setProxyPassword(String proxyPassword) {
        this.proxyPassword = proxyPassword;
    }

    public String getProxyDomain() {
        return proxyDomain;
    }

    public void setProxyDomain(String proxyDomain) {
        this.proxyDomain = proxyDomain;
    }

    public String getProxyWorkstation() {
        return proxyWorkstation;
    }

    public void setProxyWorkstation(String proxyWorkstation) {
        this.proxyWorkstation = proxyWorkstation;
    }

    public boolean isSupportCname() {
        return supportCname;
    }

    public void setSupportCname(boolean supportCname) {
        this.supportCname = supportCname;
    }

    public List<String> getCnameExcludeList() {
        return cnameExcludeList;
    }

    public void setCnameExcludeList(List<String> cnameExcludeList) {
        this.cnameExcludeList = cnameExcludeList;
    }

    public boolean isSldEnabled() {
        return sldEnabled;
    }

    public void setSldEnabled(boolean sldEnabled) {
        this.sldEnabled = sldEnabled;
    }

    public int getRequestTimeout() {
        return requestTimeout;
    }

    public void setRequestTimeout(int requestTimeout) {
        this.requestTimeout = requestTimeout;
    }

    public boolean isRequestTimeoutEnabled() {
        return requestTimeoutEnabled;
    }

    public void setRequestTimeoutEnabled(boolean requestTimeoutEnabled) {
        this.requestTimeoutEnabled = requestTimeoutEnabled;
    }

    public long getSlowRequestsThreshold() {
        return slowRequestsThreshold;
    }

    public void setSlowRequestsThreshold(long slowRequestsThreshold) {
        this.slowRequestsThreshold = slowRequestsThreshold;
    }

    public Map<String, String> getDefaultHeaders() {
        return defaultHeaders;
    }

    public void setDefaultHeaders(Map<String, String> defaultHeaders) {
        this.defaultHeaders = defaultHeaders;
    }

    public boolean isCrcCheckEnabled() {
        return crcCheckEnabled;
    }

    public void setCrcCheckEnabled(boolean crcCheckEnabled) {
        this.crcCheckEnabled = crcCheckEnabled;
    }

    public SignVersion getSignatureVersion() {
        return signatureVersion;
    }

    public void setSignatureVersion(SignVersion signatureVersion) {
        this.signatureVersion = signatureVersion;
    }

    public long getTickOffset() {
        return tickOffset;
    }

    public void setTickOffset(long tickOffset) {
        this.tickOffset = tickOffset;
    }

    public boolean isRedirectEnable() {
        return redirectEnable;
    }

    public void setRedirectEnable(boolean redirectEnable) {
        this.redirectEnable = redirectEnable;
    }

    public boolean isVerifySSLEnable() {
        return verifySSLEnable;
    }

    public void setVerifySSLEnable(boolean verifySSLEnable) {
        this.verifySSLEnable = verifySSLEnable;
    }

    public boolean isLogConnectionPoolStats() {
        return logConnectionPoolStats;
    }

    public void setLogConnectionPoolStats(boolean logConnectionPoolStats) {
        this.logConnectionPoolStats = logConnectionPoolStats;
    }

    public boolean isUseSystemPropertyValues() {
        return useSystemPropertyValues;
    }

    public void setUseSystemPropertyValues(boolean useSystemPropertyValues) {
        this.useSystemPropertyValues = useSystemPropertyValues;
    }
}
