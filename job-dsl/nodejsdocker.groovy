job('NodeJS Docker example') {
    scm {
        git('git://github.com/chenchienlin/docker-demo.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('Jack Lin')
            node / gitConfigEmail('x91003502@gmail.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('nodejs') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('chenchienlin/docker-nodejs-demo')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('chenchienlin')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
