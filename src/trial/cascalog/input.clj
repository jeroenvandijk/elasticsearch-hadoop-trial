(ns trial.cascalog.input
 (:import [org.elasticsearch.hadoop.cascading ESTap]
          [cascading.tuple Fields])
 (:require [cascalog.api :refer :all]
           [cascalog.workflow :as w]
 
           [cascalog.more-taps :as more]))

;; lein with-profile cascalog run -m trial.cascalog.input
(defn -main []
  (?- (ESTap. "radio/artists" (w/fields ["?name" "?url" "?picture"]))
      (<- [?name ?url ?picture] ([["jeroen" "http://github.com/jeroenvandijk" "https://secure.gravatar.com/avatar/3e626f6013805e96a95e999fc691b21f?s=420"]]
                                 ?name ?url ?picture))))

;; lein with-profile cascalog run -m trial.cascalog.input/delimited
(defn delimited []
  (?- (ESTap. "radio/artists" (w/fields ["?name" "?url" "?picture"]))
      (<- [?name ?url ?picture]
          ((more/lfs-delimited "resources/artists.dat" :outfields ["?id" "?name" "?url" "?picture"]) _ ?name ?url ?picture))))

; Works perfectly without an error at the end
; Performing task 'run' with profile(s): 'cascalog'
; 13/05/09 11:41:13 INFO util.HadoopUtil: resolving application jar from found main method on: clojure.main
; 13/05/09 11:41:13 INFO planner.HadoopPlanner: using application jar: /Users/jeroen/.m2/repository/org/clojure/clojure/1.5.1/clojure-1.5.1.jar
; 13/05/09 11:41:13 INFO property.AppProps: using app.id: 9B959D9D7D45C8BD40686AEE7861220A
; 2013-05-09 11:41:13.211 java[63452:1203] Unable to load realm info from SCDynamicStore
; 13/05/09 11:41:13 INFO util.Version: Concurrent, Inc - Cascading 2.1.5
; 13/05/09 11:41:13 INFO flow.Flow: [] starting
; 13/05/09 11:41:13 INFO flow.Flow: []  source: Lfs["TextDelimited[['?id', '?name', '?url', '?picture']]"]["resources/artists.dat"]
; 13/05/09 11:41:13 INFO flow.Flow: []  sink: ESHadoopTap["ESHadoopScheme[['?name', '?url', '?picture']]"]["radio/artists"]
; 13/05/09 11:41:13 INFO flow.Flow: []  parallel execution is enabled: false
; 13/05/09 11:41:13 INFO flow.Flow: []  starting jobs: 1
; 13/05/09 11:41:13 INFO flow.Flow: []  allocating threads: 1
; 13/05/09 11:41:13 INFO flow.FlowStep: [] starting step: (1/1) radio/artists
; 13/05/09 11:41:13 WARN util.NativeCodeLoader: Unable to load native-hadoop library for your platform... using builtin-java classes where applicable
; 13/05/09 11:41:13 WARN snappy.LoadSnappy: Snappy native library not loaded
; 13/05/09 11:41:13 INFO mapred.FileInputFormat: Total input paths to process : 1
; 13/05/09 11:41:13 INFO flow.FlowStep: [] submitted hadoop job: job_local_0001
; 13/05/09 11:41:13 INFO mapred.Task:  Using ResourceCalculatorPlugin : null
; 13/05/09 11:41:13 INFO hadoop.TupleSerialization: using default comparator: cascalog.hadoop.DefaultComparator
; 13/05/09 11:41:13 INFO io.MultiInputSplit: current split input path: file:/Users/jeroen/Projects/Github/me/elasticsearch-hadoop-trial/resources/artists.dat
; 13/05/09 11:41:13 INFO mapred.MapTask: numReduceTasks: 0
; 13/05/09 11:41:13 INFO hadoop.FlowMapper: cascading version: Concurrent, Inc - Cascading 2.1.5
; 13/05/09 11:41:13 INFO hadoop.FlowMapper: child jvm opts: -Xmx200m
; 13/05/09 11:41:13 INFO hadoop.FlowMapper: sourcing from: Lfs["TextDelimited[['?id', '?name', '?url', '?picture']]"]["resources/artists.dat"]
; 13/05/09 11:41:13 INFO hadoop.FlowMapper: sinking to: ESHadoopTap["ESHadoopScheme[['?name', '?url', '?picture']]"]["radio/artists"]
; 13/05/09 11:41:14 INFO mapred.Task: Task:attempt_local_0001_m_000000_0 is done. And is in the process of commiting
; 13/05/09 11:41:16 INFO mapred.LocalJobRunner: file:/Users/jeroen/Projects/Github/me/elasticsearch-hadoop-trial/resources/artists.dat:0+104591
; 13/05/09 11:41:16 INFO mapred.Task: Task 'attempt_local_0001_m_000000_0' done.