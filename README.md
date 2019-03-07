# spring-boot-elk-example
1. Install **E**lasticsearch, **L**ogstash, **K**ibana
2. Configure `kibana.xml`
```
vim /usr/local/etc/kibana/kibana.xml

elasticsearch.hosts: ["http://localhost:9200"]
```
3. Create a directory for logstash conf   
`mkdir /usr/local/etc/logstash/conf.d`

4. Add logstash configuration to `conf.d`  
`vim example.conf`
* example.conf
```
input {
    file {
         path => "/you/log/file/directory/*.log"
         codec => "json"
         type => "logback"
    }
}

output {
    if [type] == "logback" {
        elasticsearch {
            hosts => [ "localhost:9200" ]
            index => "logback-%{+YYYY.MM.dd}"
        }
    }
}
```
5. Start spring boot application

6. Run logstash with new configuration   
`logstash -f example.conf`

7. Checkout log on [Kibana](http://localhost:5601)