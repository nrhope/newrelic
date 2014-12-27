def repo_owner = "escapestudios-cookbooks"
def repo_name = "newrelic"
def branches = [ "master" ] as String[]

branches.each {
    def branch = it

    job {
      name "${repo_owner}/${repo_name}_${branch}".replaceAll("/","-")
      scm {
        git("git://github.com/${repo_owner}/${repo_name}.git", branch)
      }
      triggers {
        githubPush()
      }
      steps {
        rake {
            task("travis")
        }
      }
      publishers {
        mailer("dev+newrelic@escapestudios.com")
      }
    }
}
