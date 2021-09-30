package com.kamijoucen.jsonar.common.io

import java.io.File
import java.util.concurrent.ForkJoinPool
import java.util.concurrent.RecursiveTask

class CodeFileFinder(
    private val basePath: String,
    private var suffix: String,
) {
    fun getCodePath(): List<String> {
        val baseDir = File(basePath)
        if (!baseDir.exists() || !baseDir.isDirectory) {
            return emptyList()
        }
        return ForkJoinPool(Runtime.getRuntime().availableProcessors() + 1)
            .invoke(FindTask(basePath, suffix))
    }

    private class FindTask(val path: String, val suffix: String) : RecursiveTask<List<String>>() {
        override fun compute(): List<String> {
            val subFiles = File(path).listFiles()
            if (subFiles == null || subFiles.isEmpty()) {
                return emptyList()
            }
            val subTask = ArrayList<FindTask>()
            val result = ArrayList<String>()
            for (subFile in subFiles) {
                if (subFile.isDirectory) {
                    subTask.add(FindTask(subFile.absolutePath, suffix))
                } else {
                    if (subFile.name.endsWith(suffix)) {
                        result.add(subFile.absolutePath)
                    }
                }
            }
            for (task in invokeAll(subTask)) {
                result.addAll(task.join())
            }
            return result
        }
    }
}