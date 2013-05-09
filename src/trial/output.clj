(ns trial.output
 (:import [org.elasticsearch.hadoop.cascading ESTap]
          [cascading.pipe Pipe]
          [cascading.tap.local StdOutTap]
          [cascading.flow.local LocalFlowConnector]
          [cascading.scheme.local TextLine]))

;; lein run -m trial.output
(defn -main []
  (let [in (ESTap. "_search?q=*")
        out (StdOutTap. (TextLine.))]
    (.. (LocalFlowConnector.) (connect in out (Pipe. "read-from-ES")) complete)))

; Works as expected:
;  13/05/09 11:36:31 INFO property.AppProps: using app.id: 8C9ECBD66D4BE4200787923D61AEE6B5
;  13/05/09 11:36:31 INFO util.Version: Concurrent, Inc - Cascading 2.1.5
;  13/05/09 11:36:31 INFO flow.Flow: [read-from-ES] starting
;  13/05/09 11:36:31 INFO flow.Flow: [read-from-ES]  source: ESLocalTap["ESLocalScheme[[UNKNOWN]->[ALL]]"]["_search?q=*"]
;  13/05/09 11:36:31 INFO flow.Flow: [read-from-ES]  sink: StdOutTap["TextLine[['num', 'line']->[ALL]]"]["stdOut"]
;  13/05/09 11:36:31 INFO flow.Flow: [read-from-ES]  parallel execution is enabled: true
;  13/05/09 11:36:31 INFO flow.Flow: [read-from-ES]  starting jobs: 1
;  13/05/09 11:36:31 INFO flow.Flow: [read-from-ES]  allocating threads: 1
;  13/05/09 11:36:31 INFO flow.FlowStep: [read-from-ES] starting step: local
;  radio	artists	qHzgQuZbRw68X-fWt9U9Zw	1.0	{?picture=http://userserve-ak.last.fm/serve/252/51273485.jpg, ?url=http://www.last.fm/music/Combichrist, ?name=Combichrist}
;  radio	artists	_o1cMmjfTw-YEDkC1mlwdA	1.0	{?picture=http://userserve-ak.last.fm/serve/252/5872875.jpg, ?url=http://www.last.fm/music/Grendel, ?name=Grendel}
;  radio	artists	OIlD0d-LSiqswjcT8hRZ7w	1.0	{?picture=http://userserve-ak.last.fm/serve/252/34892635.jpg, ?url=http://www.last.fm/music/Hocico, ?name=Hocico}
;  radio	artists	80Gp6IQIRyeAtnswLmteCA	1.0	{?picture=http://userserve-ak.last.fm/serve/252/371747.jpg, ?url=http://www.last.fm/music/Icon+of+Coil, ?name=Icon of Coil}
; ....
; ....