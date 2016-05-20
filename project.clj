(defn env-lang->jvm-opts []
  (let [env-lang (System/getenv "LANG")
        [lang region] (if env-lang (clojure.string/split env-lang #"_" 2))]
    (if (and lang region)
      [(str "-Duser.language=" lang) (str "-Duser.region=" region)])))

(defproject puppetlabs/i18n "0.3.1-SNAPSHOT"
  :description "Clojure i18n library"
  :url "http://github.com/puppetlabs/clj-i18n"
  :license {:name "Apache License, Version 2.0"
            :url "http://www.apache.org/licenses/LICENSE-2.0"}

  :pedantic? :abort
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.gnu.gettext/libintl "0.18.3"]]

  :jvm-opts ~(env-lang->jvm-opts)

  :main puppetlabs.i18n.main
  :aot [puppetlabs.i18n.main]

  :deploy-repositories [["releases" {:url "https://clojars.org/repo"
                                     :username :env/clojars_jenkins_username
                                     :password :env/clojars_jenkins_password
                                     :sign-releases false}]])
