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
* logfile-example.conf (Read logfile)
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
* http-api-example.conf (Trigger HTTP Request)
```
input {
    http_poller {
        urls => {
            welcome => {
                method => "GET"
                user => ""
                password => ""
                url => "http://localhost:8080/welcome/logstash"
                headers => {
                    Accept => "application/json"
                }
            }
        }
        request_timeout => 60
        schedule => {
            every => "5s"
        }
        codec => "json"
        metadata_target => "http_poller_metadata"
    }
}

output {
    elasticsearch {
        hosts => [ "localhost:9200" ]
        index => "http-poller"
    }
}
```
5. Start spring boot application

6. Run logstash with new configuration   
`logstash -f example.conf`

7. Checkout log on [Kibana](http://localhost:5601)

8. Utilize Spring Boot Actuator to monitor your service
- http://your.host/actuator/health  
- http://your.host/actuator/metrics/http.server.requests  
- http://your.host/actuator/httptrace  

9. Create the dashboard like so