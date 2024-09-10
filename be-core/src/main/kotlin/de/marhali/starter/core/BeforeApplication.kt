package de.marhali.starter.core

import co.elastic.apm.attach.ElasticApmAttacher

fun runBeforeApplication() {
	ElasticApmAttacher.attach()
}
