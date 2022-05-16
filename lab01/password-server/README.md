基于密码模式+Spring Security OAuth2的最简授权服务器
======

# 操作方式

### 1. 获取访问令牌

curl -X POST --user app:user http://localhost:8080/oauth/token -H "accept: application/json" -H "content-type: application/x-www-form-urlencoded" -d "grant_type=password&username=oauth_admin&password=user&scope=read"

响应案例：

```json
{
    "access_token": "58a02fd5-87f5-44ff-bbdd-d429cf6a2f60",
    "token_type": "bearer",
    "expires_in": 43199,
    "scope": "read"
}
```

### 2. 调用API

curl -X GET http://localhost:8081/api/userinfo -H "authorization: Bearer 58a02fd5-87f5-44ff-bbdd-d429cf6a2f60"

案例响应：

```json
{
    "name": "bobo",
    "email": "bobo@spring2go.com"
}
```