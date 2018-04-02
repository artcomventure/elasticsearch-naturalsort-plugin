# ElasticSearch NaturalSort Plugin 

The plugin adds naturalsort Analyser to an ElasticSearch index.

## Installation

Target folder contains compiled binaries ready for use. The first 3 numbers of the version tag refers to the version of ElasticSearch the plugin built for.

* ElasticSearch 2.4.6

``` bash
plugin install https://github.com/artcomventure/elasticsearch-naturalsort-plugin/raw/master/target/elasticsearch-naturalsort-plugin-2.4.6.1.zip
```

* ElasticSearch 2.4.5

``` bash
plugin install https://github.com/artcomventure/elasticsearch-naturalsort-plugin/raw/master/target/elasticsearch-naturalsort-plugin-2.4.5.1.zip
```


Restart ElasticSearch.

## Use case

Set naturalsort to myvalue.mysort of the myindex mapping:

```json
  "mappings": {
    "myindex": {
      "properties": {
        "myvalue": {
          "index": "not_analyzed",
          "type": "string",
          "fields": {
            "mysort": {
              "analyzer": "naturalsort",
              "type": "string"
            }
          }
        }
      }
    }
  }
```

Index some items, then compare

``` bash
curl -XGET 'http://127.0.0.1:9200/myindex/items/_search?pretty' -d '
{
  "sort": {
    "myvalue": "asc"
  }
}
'
```

and

``` bash
curl -XGET 'http://127.0.0.1:9200/myindex/items/_search?pretty' -d '
{
  "sort": {
    "myvalue.mysort": "asc"
  }
}
'
```

