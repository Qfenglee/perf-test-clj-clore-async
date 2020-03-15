# perf-test-clj-clore-async

info:
this program is a basic perf tool for clojure core.async go schedule performance
you can setting how much go-thread your msg will pass as simple increment to a long value as msg:eg 100,each msg will inc 100 times through 100 go-thread

and you can setting how much msg you wish to calc in the queue process:eg 10000
the third params is for you to setting each channel buffer size:eg 10/100
the forth one is core.async pool-size :eg: 8
the last one is setting for weather you will to mock heavy work in each xform function: eg: true

	Result: latency of your first msg processed,
			latency of your last msg processed, 
			the total time consumerd to process all your msg

## Installation

    lein uberjar

## Usage

    lein run {cnt-go-thread} {cnt-msg} {buffer-size} {pool-size} {true/false as is add heavy task per each go calc function

## Examples
    eg: lein run 100 10000 10 8 true

    begin ts:           #inst "2020-03-15T15:20:12.509-00:00"
    first pkg begin-ts: #inst "2020-03-15T15:20:12.520-00:00"
    first pkg end-ts    #inst "2020-03-15T15:20:12.528-00:00"
    last pkg begin-ts:  #inst "2020-03-15T15:20:23.580-00:00"
    last pkg end-ts     #inst "2020-03-15T15:20:23.580-00:00"
    "Elapsed time: 11060.798894 msecs"


## License

Copyright © 2020

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
