/*
 * Run Task Gradle Plugins
 * Copyright (c) 2024 Jason Penilla
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package xyz.jpenilla.runtask.pluginsapi

import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import xyz.jpenilla.runtask.util.HashingAlgorithm
import xyz.jpenilla.runtask.util.calculateHash
import xyz.jpenilla.runtask.util.toHexString
import kotlin.io.byteInputStream

public sealed class PluginApiDownload

public abstract class HangarApiDownload : PluginApiDownload() {

  @get:Input
  public abstract val url: Property<String>

  @get:Input
  public abstract val plugin: Property<String>

  @get:Input
  public abstract val version: Property<String>

  override fun toString(): String {
    return "HangarApiDownload{url=${url.get()}, plugin=${plugin.get()}, version=${version.get()}}"
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) {
      return true
    }
    if (javaClass != other?.javaClass) {
      return false
    }

    other as HangarApiDownload

    return url.get() == other.url.get() &&
      plugin.get() == other.plugin.get() &&
      version.get() == other.version.get()
  }

  override fun hashCode(): Int {
    var result = url.get().hashCode()
    result = 31 * result + plugin.get().hashCode()
    result = 31 * result + version.get().hashCode()
    return result
  }
}

public abstract class ModrinthApiDownload : PluginApiDownload() {

  @get:Input
  public abstract val url: Property<String>

  @get:Input
  public abstract val id: Property<String>

  @get:Input
  public abstract val version: Property<String>

  override fun toString(): String {
    return "ModrinthApiDownload{url=${url.get()}, id=${id.get()}, version=${version.get()}}"
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) {
      return true
    }
    if (javaClass != other?.javaClass) {
      return false
    }

    other as ModrinthApiDownload

    return url.get() == other.url.get() &&
      id.get() == other.id.get() &&
      version.get() == other.version.get()
  }

  override fun hashCode(): Int {
    var result = url.get().hashCode()
    result = 31 * result + id.get().hashCode()
    result = 31 * result + version.get().hashCode()
    return result
  }
}

public abstract class GitHubApiDownload : PluginApiDownload() {

  @get:Input
  public abstract val owner: Property<String>

  @get:Input
  public abstract val repo: Property<String>

  @get:Input
  public abstract val tag: Property<String>

  @get:Input
  public abstract val assetName: Property<String>

  override fun toString(): String {
    return "GitHubApiDownload{owner=${owner.get()}, repo=${repo.get()}, tag=${tag.get()}, assetName=${assetName.get()}}"
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) {
      return true
    }
    if (javaClass != other?.javaClass) {
      return false
    }

    other as GitHubApiDownload

    return owner.get() == other.owner.get() &&
      repo.get() == other.repo.get() &&
      tag.get() == other.tag.get() &&
      assetName.get() == other.assetName.get()
  }

  override fun hashCode(): Int {
    var result = owner.get().hashCode()
    result = 31 * result + repo.get().hashCode()
    result = 31 * result + tag.get().hashCode()
    result = 31 * result + assetName.get().hashCode()
    return result
  }
}

public abstract class UrlDownload : PluginApiDownload() {

  @get:Input
  public abstract val url: Property<String>

  override fun toString(): String {
    return "UrlDownload{url=${url.get()}}"
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) {
      return true
    }
    if (javaClass != other?.javaClass) {
      return false
    }

    other as UrlDownload

    return url.get() == other.url.get()
  }

  override fun hashCode(): Int {
    return url.hashCode()
  }

  internal fun urlHash(): String {
    return toHexString(url.get().byteInputStream().calculateHash(HashingAlgorithm.SHA1))
  }
}

public abstract class DiscordApiDownload : PluginApiDownload() {
  @get:Input
  public abstract val channelId: Property<String>

  @get:Input
  public abstract val messageId: Property<String>

  @get:Input
  public abstract val token: Property<String>

  override fun toString(): String {
    return "DiscordApiDownload{channelId=${channelId.get()}, messageId=${messageId.get()}, token=${"*".repeat(token.get().length)}}"
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) {
      return true
    }
    if (javaClass != other?.javaClass) {
      return false
    }

    other as DiscordApiDownload

    return channelId.get() == other.channelId.get() &&
      messageId.get() == other.messageId.get() &&
      token.get() == other.token.get()
  }

  override fun hashCode(): Int {
    var result = channelId.get().hashCode()
    result = 31 * result + messageId.get().hashCode()
    result = 31 * result + token.get().hashCode()
    return result
  }

}
