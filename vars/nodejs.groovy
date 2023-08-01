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
        }                                                                   // End of the stages
    }
}




            //    script { 
            //         sample.info('CATALOGUE')
            //     }