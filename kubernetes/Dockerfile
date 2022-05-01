#######
# BUILD
#######

FROM clojure:openjdk-11-tools-deps-slim-buster AS clojure

WORKDIR /build

COPY deps.edn /
RUN clojure -P

COPY . .
RUN clojure -T:build uber

#########
# PACKAGE
#########

FROM spark-base/spark:latest

COPY --from=clojure /build/target/geni-kubernetes-starter.jar /app/geni-kubernetes-starter.jar