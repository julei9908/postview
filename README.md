# postview
前后端分离的后端系统

### RSA256公私钥生成
    
    1. openssl genrsa -out rsaprivatekey.pem 2048

    2. openssl rsa -in rsaprivatekey.pem -out public.pem -pubout

    3. openssl pkcs8 -topk8 -in rsaprivatekey.pem -out private.pem -nocrypt
