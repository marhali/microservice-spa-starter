package de.marhali.starter.shared

import co.elastic.apm.attach.ElasticApmAttacher

fun runBootstrap() {
	// Use self generated CA which has been installed on OS
	System.setProperty("javax.net.ssl.trustStoreType", "KeychainStore")
	ElasticApmAttacher.attach()
}
