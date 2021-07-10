Because I am not familiar with SpringBoot and the web, I spent some time learning them and
setting the environment (around two hours). After that, I took about one hour to complete
this exercise. This is what I could do in the time limit. And I don't have any idea about bonus
requirements. Sorry for that bad solution. If possible, please give me some feedback about what
I could learn. Thanks.

In my computer, port 8080 doesn't work, so I change it to 8000.

The command:
```$xslt
echo {"pickupPostcode": "SW1A1AA", "deliveryPostcode": "EC2A3LT" } | \
curl -d @- http://localhost:8000/quote --header "Content-Type:application/json"
```
will return:
```$xslt
{"pickupPostcode":"SW1A1AA","deliveryPostcode":"EC2A3LT","vehicle":null,"price":316}
```

And:

```$xslt
{"pickupPostcode": "SW1A1AA", "deliveryPostcode": "EC2A3LT", "vehicle": "bicycle" } | \
curl -d @- http://localhost:8000/quote --header "Content-Type:application/json
```
will return:
```$xslt
{"pickupPostcode":"SW1A1AA","deliveryPostcode":"EC2A3LT","vehicle":"bicycle","price":348}
```