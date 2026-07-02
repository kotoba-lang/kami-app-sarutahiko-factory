(ns kotoba.sarutahiko-factory.fixtures
  "JVM resource loader for this repo's synthetic sarutahiko-factory EDN
  fixtures (`resources/kotoba/sarutahiko-factory/*.edn`).

  The real upstream scene dataset
  (`70-tools/e7m-sim/scenes/sarutahiko-factory-r0/{factory.scene,
  construction.order, clashes, robots, production.order}.json`, referenced
  by the original Rust crate's `include_str!`) is **not present anywhere
  in this monorepo checkout** — confirmed absent by a broad search before
  this port began. Rather than fabricate that dataset, this repo ships
  small hand-authored EDN fixtures under `resources/kotoba/sarutahiko-
  factory/` exercising the same shape of properties the original Rust
  tests checked, and this namespace is the only I/O in the repo: a thin
  `clojure.java.io/resource` + `clojure.edn/read-string` loader, mirroring
  `kotoba-lang/kami-scene-contracts`'s I/O-loader namespaces. Plain
  `.clj` (JVM classpath resources only) rather than `.cljc` — there is
  nothing to bundle a browser-side loader for yet, and every other
  namespace in this repo (the actual domain contract) is `.cljc` and has
  no I/O."
  (:require [clojure.edn :as edn]
            [clojure.java.io :as io]))

(defn- load-edn [path]
  (if-let [r (io/resource path)]
    (edn/read-string (slurp r))
    (throw (ex-info "missing sarutahiko-factory fixture resource" {:path path}))))

(defn factory
  "The synthetic `:factory/*` map (see `kotoba.sarutahiko-factory.factory`)."
  []
  (load-edn "kotoba/sarutahiko-factory/factory.edn"))

(defn construction-order
  "The synthetic `:construction-order/*` map (see
  `kotoba.sarutahiko-factory.construction-order`)."
  []
  (load-edn "kotoba/sarutahiko-factory/construction-order.edn"))

(defn robots
  "The synthetic `:robots/*` map (see `kotoba.sarutahiko-factory.robots`)."
  []
  (load-edn "kotoba/sarutahiko-factory/robots.edn"))

(defn prod-order
  "The synthetic `:prod-order/*` map (see
  `kotoba.sarutahiko-factory.prod-order`)."
  []
  (load-edn "kotoba/sarutahiko-factory/prod-order.edn"))

(defn clashes
  "The synthetic `:clashes/*` map (see `kotoba.sarutahiko-factory.clashes`)."
  []
  (load-edn "kotoba/sarutahiko-factory/clashes.edn"))
