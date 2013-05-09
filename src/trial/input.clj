(ns trial.input
 (:import [org.elasticsearch.hadoop.cascading ESTap]
          [cascading.tuple Fields]
          [cascading.pipe Pipe]
          [cascading.tap.hadoop Lfs]
          [cascading.scheme.hadoop TextDelimited]
          [cascading.flow.hadoop HadoopFlowConnector]))

;; lein run -m trial.input
(defn -main []
  (let [in (Lfs. (TextDelimited.
                   (Fields. (into-array String ["?id" "?name", "?url", "?picture"]))) "resources/artists.dat")
        out (ESTap. "radio/artists" (Fields. (into-array String ["?name", "?url", "?picture"])))]
    (.. (HadoopFlowConnector.)
        (connect in out (Pipe. "write-to-ES"))
        complete)))

; Works as expected, but there is a suspicious Exception at the end

; 13/05/09 11:39:07 INFO util.HadoopUtil: resolving application jar from found main method on: clojure.main
; 13/05/09 11:39:07 INFO planner.HadoopPlanner: using application jar: /Users/jeroen/.m2/repository/org/clojure/clojure/1.5.1/clojure-1.5.1.jar
; 13/05/09 11:39:07 INFO property.AppProps: using app.id: 354B111A26D1C5998EE69C07A0591798
; 13/05/09 11:39:08 INFO util.Version: Concurrent, Inc - Cascading 2.1.5
; 13/05/09 11:39:08 INFO flow.Flow: [write-to-ES] starting
; 13/05/09 11:39:08 INFO flow.Flow: [write-to-ES]  source: Lfs["TextDelimited[['?id', '?name', '?url', '?picture']]"]["resources/artists.dat"]
; 13/05/09 11:39:08 INFO flow.Flow: [write-to-ES]  sink: ESHadoopTap["ESHadoopScheme[['?name', '?url', '?picture']]"]["radio/artists"]
; 13/05/09 11:39:08 INFO flow.Flow: [write-to-ES]  parallel execution is enabled: false
; 13/05/09 11:39:08 INFO flow.Flow: [write-to-ES]  starting jobs: 1
; 13/05/09 11:39:08 INFO flow.Flow: [write-to-ES]  allocating threads: 1
; 13/05/09 11:39:08 INFO flow.FlowStep: [write-to-ES] starting step: (1/1) radio/artists
; 13/05/09 11:39:08 INFO jvm.JvmMetrics: Initializing JVM Metrics with processName=JobTracker, sessionId=
; 13/05/09 11:39:08 INFO mapred.FileInputFormat: Total input paths to process : 1
; 13/05/09 11:39:08 INFO flow.FlowStep: [write-to-ES] submitted hadoop job: job_local_0001
; 13/05/09 11:39:08 INFO mapred.FileInputFormat: Total input paths to process : 1
; 13/05/09 11:39:08 INFO io.MultiInputSplit: current split input path: file:/Users/jeroen/Projects/Github/me/elasticsearch-hadoop-trial/resources/artists.dat
; 13/05/09 11:39:08 INFO mapred.MapTask: numReduceTasks: 0
; 13/05/09 11:39:08 INFO hadoop.FlowMapper: cascading version: Concurrent, Inc - Cascading 2.1.5
; 13/05/09 11:39:08 INFO hadoop.FlowMapper: child jvm opts: -Xmx200m
; 13/05/09 11:39:08 INFO hadoop.FlowMapper: sourcing from: Lfs["TextDelimited[['?id', '?name', '?url', '?picture']]"]["resources/artists.dat"]
; 13/05/09 11:39:08 INFO hadoop.FlowMapper: sinking to: ESHadoopTap["ESHadoopScheme[['?name', '?url', '?picture']]"]["radio/artists"]
; 13/05/09 11:39:08 INFO mapred.TaskRunner: Task:attempt_local_0001_m_000000_0 is done. And is in the process of commiting
; 13/05/09 11:39:08 INFO mapred.LocalJobRunner: file:/Users/jeroen/Projects/Github/me/elasticsearch-hadoop-trial/resources/artists.dat:0+104591
; 13/05/09 11:39:08 INFO mapred.TaskRunner: Task 'attempt_local_0001_m_000000_0' done.
; Exception in thread "Thread-13" java.lang.AbstractMethodError: org.apache.hadoop.mapred.OutputCommitter.cleanupJob(Lorg/apache/hadoop/mapred/JobContext;)V
; 	at org.apache.hadoop.mapred.LocalJobRunner$Job.run(LocalJobRunner.java:245)

; Flow has to be killed:
; ^C13/05/09 11:40:29 INFO flow.Flow: [write-to-ES] shutdown hook calling stop on flow
; 13/05/09 11:40:29 INFO flow.Flow: [write-to-ES] stopping all jobs
; 13/05/09 11:40:29 INFO flow.FlowStep: [write-to-ES] stopping: (1/1) radio/artists
; 13/05/09 11:40:29 INFO flow.Flow: [write-to-ES] stopped all jobs