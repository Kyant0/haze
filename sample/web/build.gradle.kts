// Copyright 2024, Christopher Banes and the Haze project contributors
// SPDX-License-Identifier: Apache-2.0


import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl

plugins {
  id("dev.chrisbanes.kotlin.multiplatform")
  id("dev.chrisbanes.compose")
}

kotlin {
  @OptIn(ExperimentalWasmDsl::class)
  wasmJs {
    browser {
      commonWebpackConfig {
        outputFileName = "sample.js"
      }
    }

    binaries.executable()
  }

  js(IR) {
    browser {
      commonWebpackConfig {
        outputFileName = "sample.js"
      }
    }

    binaries.executable()
  }

  sourceSets {
    commonMain {
      dependencies {
        implementation(projects.sample.shared)
      }
    }

    jsMain {
      dependencies {
        // Coil fix
        implementation(npm("node-polyfill-webpack-plugin", "^4.0.0"))
      }
    }
  }
}
