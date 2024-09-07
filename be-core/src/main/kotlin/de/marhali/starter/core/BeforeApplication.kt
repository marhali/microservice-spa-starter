package de.marhali.starter.core

import co.elastic.apm.attach.ElasticApmAttacher

fun runBeforeApplication() {
	// Use self generated CA which has been installed on OS
	System.setProperty("javax.net.ssl.trustStoreType", "KeychainStore")
	ElasticApmAttacher.attach()
}
