# elasticsearch-hadoop-trial

Experiment in using ElasticSearch with Cascading and Cascalog

## Usage

Install ElasticSearch (e.g via brew)

    brew install elasticsearch

Install Leiningen

    brew install leiningen
    
## Run scripts

### Cascading versions
(adopted from https://github.com/elasticsearch/elasticsearch-hadoop#cascading)

Input works, but exception at the end, see [output](https://github.com/jeroenvandijk/elasticsearch-hadoop-trial/blob/master/src/trial/input.clj)
    
    lein run -m trial.input

Ouptut works perfectly, see [output](https://github.com/jeroenvandijk/elasticsearch-hadoop-trial/blob/master/src/trial/output.clj)
    
    lein run -m trial.output

### Cascalog versions

Input works perfectly, see [output](https://github.com/jeroenvandijk/elasticsearch-hadoop-trial/blob/master/src/trial/cascalog/input.clj)
    
    lein with-profile cascalog run -m trial.cascalog.input

Ouptut doesn't work, see [output](https://github.com/jeroenvandijk/elasticsearch-hadoop-trial/blob/master/src/trial/cascalog/output.clj)
    
    lein with-profile cascalog run -m trial.cascalog.output

## License

Copyright © 2013 FIXME

Distributed under the Eclipse Public License, the same as Clojure.
