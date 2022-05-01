# geni-kubernetes-starter

[Geni](https://cljdoc.org/d/zero.one/geni/0.0.40/doc/readme) is used by the Hotpot team at [Credit Suisse Hong Kong](https://www.credit-suisse.com/careers/en.html) to run Apache Spark jobs on [Openshift/Kubernetes](https://www.redhat.com/en/technologies/cloud-computing/openshift).

This repository is an attempt to share how we use geni in production.

## Pre-requisites:

1. Java 11
1. Apache Spark

## Usage

Run the project's tests:

    $ clojure -M:runner

Build project's uberjar:

    $ clojure -T:build uber

## Acknowledgements

I just refactored/simplified the scaffolding to work outside the corporate intranet on MiniKube. The original architects/authors are [@gnarroway](https://github.com/gnarroway) and [@davzucky](https://github.com/davzucky).

