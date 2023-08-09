# Simulação de bucket S3 sendo executado localmente
Durante o desenvolvimento em cloud é comum a necessidade de testar recursos de cloud de forma local, um exemplo é o bucket S3 da Amazon, como é um recurso pago, não seria viável ter uma palicação executando localmente apontando para recursos existentes, acasionando em gastos desnecessários, uma boa forma de realizar esses testes é utilizar um bucket "fake", nesse projeto vamos demonstrar duas possibilidades de uso, localstak e minio.
* [LocalStack](https://localstack.cloud/)
* [Minio](https://min.io/)
## 📋 Pré-requisitos
* Java versão: 1.8
* Dokcer versão: 20.10.8
* Docker-compose versão: 1.29.2
## 🔧 Instalação
1 - Subir as intâncias dos buckets, localstack S3 e Minio.
Navegar até o repositório raiz do projeto: *./s3-bucket-localstack-demo*.
```
dokcer-compose up
```
Após subir as imagens, será possível escolher qual ferramenta deseja utilizar para simular o buket S3, localstak ou minio.
O localstak é comumente utilizado, possuindo mais de 45k de github⭐, porém o minio possui uma interface muito intuitiva, podendo ser acessado pelo link: `http://localhost:9001/`

2 - Execute a aplicação java em sua IDE preferida, nesse projeto foi utilizado o *Spring Tool Suite 4 (version: 4.17.1).*

3 - Configure `spring.profiles.active` de acordo com o bucket que deseja testar, sendo:

* Minio: spring.profiles.active=minio
* LocakStack: spring.profiles.active=s3

## ⚙️ Executando
1 - Upload de um arquivo no bucket:
```
curl --request POST \
  --url http://localhost:8080/s3/upload \
  --header 'Content-Type: multipart/form-data' \
  --form 'file=@C:\Users\<myUser>\file_test.txt'
```

2 - Download de um arquivo existente no bucket
```
curl --request GET \
  --url http://localhost:8080/s3/download/{keyId}
```
