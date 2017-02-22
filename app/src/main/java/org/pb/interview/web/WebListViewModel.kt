package org.pb.interview.web

/**
 * ViewModel of Website View (@layout/fragment_weblist)
 */

class WebListViewModel(val callback: URLNavigator){
    companion object{
        private val gitHubURL = "https://github.com/"
        private val bitBucketURL = "https://bitbucket.org/"
        private val googleURL = "https://www.google.com"
    }

    //TODO: refactor to more clean code
    fun gotoGitHub(){
        callback.goToURL(gitHubURL)
    }
    fun gotoBitBucket(){
        callback.goToURL(bitBucketURL)
    }

    fun gotoGoogle(){
        callback.goToURL(googleURL)
    }
}

