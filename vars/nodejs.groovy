def lintChecks() {
    sh '''
        echo Installing JSLint for ${COMPONENT}
        npm i jslint
        /home/centos/node_modules/jslint/bin/jslint.js server.js || true
        echo Lint Check Completed for ${COMPONENT}
    '''
}

def call() {
    pipeline {
        agent { label 'WS' }
        environment {
            SONARCRED = credentials("SONARCRED")
            SONARURL = "172.31.90.194"
        }
        stages {
            stage('Lint Checks') {                                          // Start of the stages
                steps {
                    script {
                        lintChecks()
                    }
                }
            }                                                                
            stage('Code Compile') {
                steps {
                    sh "npm install"
                }
            }

            stage('Sonar Checks') {
                steps {
                    script {
                        env.ARGS="-Dsonar.sources=."
                        common.sonarChecks()
                    }
                }
            }
            stage('Testing') {
                steps {
                    sh "echo Testing In Progress"
                }
            }
        }                                                                   // End of the stages
    }
}




            //    script { 
            //         sample.info('CATALOGUE')
            //     }