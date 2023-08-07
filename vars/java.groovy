def lintChecks() {
    sh '''
        echo Performing lintChecks for ${COMPONENT}
        checkstyle:check || true
        echo Lint Checks Completed for ${COMPONENT}
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
                    sh "mvn clean compile"
                }
            }
            stage('Sonar Checks') {
                steps {
                    script {
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