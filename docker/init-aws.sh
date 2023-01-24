echo "teste script"
awslocal --endpoint-url=http://localhost:4566 s3 mb s3://my-bucket
awslocal --endpoint-url=http://localhost:4566 s3api put-bucket-acl --bucket my-bucket --acl public-read

echo "my-bucket was created"