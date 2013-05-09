(ns trial.cascalog.output
 (:import [org.elasticsearch.hadoop.cascading ESTap])
 (:require [cascalog.api :refer :all]))

;; lein with-profile cascalog run -m trial.cascalog.output  
(defn -main []
  (?- (stdout) (ESTap. "_search?q=*")))

; Performing task 'run' with profile(s): 'cascalog'
; Exception in thread "main" java.lang.NullPointerException
; 	at org.elasticsearch.hadoop.cascading.ESTap.openForRead(ESTap.java:108)
; 	at cascading.tap.Tap.openForRead(Tap.java:270)
; 	at cascading.flow.hadoop.HadoopFlowProcess.openTapForRead(HadoopFlowProcess.java:265)
; 	at cascalog.rules$pluck_tuple.invoke(rules.clj:662)
; 	at cascalog.rules$enforce_gen_schema.invoke(rules.clj:680)
; 	at clojure.core$map$fn__4207.invoke(core.clj:2487)
; 	at clojure.lang.LazySeq.sval(LazySeq.java:42)
; 	at clojure.lang.LazySeq.seq(LazySeq.java:60)
; 	at clojure.lang.RT.seq(RT.java:484)
; 	at clojure.core$seq.invoke(core.clj:133)
; 	at clojure.core$map$fn__4207.invoke(core.clj:2479)
; 	at clojure.lang.LazySeq.sval(LazySeq.java:42)
; 	at clojure.lang.LazySeq.seq(LazySeq.java:60)
; 	at clojure.lang.RT.seq(RT.java:484)
; 	at clojure.core$seq.invoke(core.clj:133)
; 	at clojure.core$apply.invoke(core.clj:617)
; 	at cascalog.api$compile_flow.doInvoke(api.clj:179)
; 	at clojure.lang.RestFn.applyTo(RestFn.java:137)
; 	at clojure.core$apply.invoke(core.clj:617)
; 	at cascalog.api$_QMARK__.doInvoke(api.clj:199)
; 	at clojure.lang.RestFn.invoke(RestFn.java:421)
; 	at trial.cascalog.output$_main.invoke(output.clj:6)
; 	at clojure.lang.Var.invoke(Var.java:411)
; 	at user$eval1875.invoke(NO_SOURCE_FILE:1)
; 	at clojure.lang.Compiler.eval(Compiler.java:6619)
; 	at clojure.lang.Compiler.eval(Compiler.java:6609)
; 	at clojure.lang.Compiler.eval(Compiler.java:6582)
; 	at clojure.core$eval.invoke(core.clj:2852)
; 	at clojure.main$eval_opt.invoke(main.clj:308)
; 	at clojure.main$initialize.invoke(main.clj:327)
; 	at clojure.main$null_opt.invoke(main.clj:362)
; 	at clojure.main$main.doInvoke(main.clj:440)
; 	at clojure.lang.RestFn.invoke(RestFn.java:421)
; 	at clojure.lang.Var.invoke(Var.java:419)
; 	at clojure.lang.AFn.applyToHelper(AFn.java:163)
; 	at clojure.lang.Var.applyTo(Var.java:532)
; 	at clojure.main.main(main.java:37)
; Error encountered performing task 'run' with profile(s): 'cascalog'
; Suppressed exit