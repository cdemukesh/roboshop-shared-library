def lintChecks() {
    sh '''
        echo Installing JSLint for ${COMPONENT}
        npm i jslint
        /home/centos/node_modules/jslint/bin/jslint.js server.js || true
        echo Lint Check Completed for ${COMPONENT}
    '''
}

def sonarChecks() {
    sh '''
        echo Sonar Checks In Progress
        sonar-scanner -Dsonar.projectKey=${COMPONENT}  -Dsonar.sources=. -Dsonar.login=c83e5f2df3ea9ac79a5029df5f37d7177535df6a -Dsonar.host.url=http://172.31.90.194:9000
        echo Sonar Checks Completed
    '''
}

def call() {
    pipeline {
        agent { label 'WS' }
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
                        sonarChecks()
                    }
                }
            }
        }                                                                   // End of the stages
    }
}




            //    script { 
            //         sample.info('CATALOGUE')
            //     }