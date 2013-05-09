(defproject elasticsearch-hadoop-trial "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"

  :jvm-opts ["-Xmx768m" "-server"]
  :repositories {"sonatype-oss" "http://oss.sonatype.org/content/repositories/snapshots"
                 "conjars.org" "http://conjars.org/repo" }

  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [
                 [org.clojure/clojure "1.5.1" :exclusions [[org.slf4j/slf4j-log4j12] [log4j]]]

                 ;; FIXME [javax.jdo/jdo2-api "2.3-ec"] can not be found. There is instead [javax.jdo/jdo2-api "2.3-eb"]
                 [org.elasticsearch/elasticsearch-hadoop "1.3.0.BUILD-SNAPSHOT" :exclusions [javax.jdo/jdo2-api]]
                ]
  :profiles { 
              :provided  { :dependencies [[org.apache.hadoop/hadoop-core "0.20.2-dev"]] }
              :cascalog { :dependencies [[cascalog "1.10.1"]] }})

