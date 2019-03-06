# spring-boot-elk-example
1. Install *E*lasticsearch, *L*ogstash, *K*ibana
2. Configure `kibana.xml`
```
vim /usr/local/etc/kibana/kibana.xml

elasticsearch.hosts: ["http://localhost:9200"]
```
3. Create a directory for logstash conf 
`mkdir /usr/local/etc/logstash/conf.d`

4. Add logstash configuration to `conf.d`
```

```
5. Checkout log on [Kibana](http://localhost:5601)