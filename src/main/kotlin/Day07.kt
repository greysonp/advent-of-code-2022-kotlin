object Day07 {
  fun p1(input: String): Long {
    val commands: List<Command> = parseCommands(input)
    val root: Directory = commands.toDirectoryStructure()
    val allDirectories = root.allSubDirectoriesIncludingSelf()

    return allDirectories
      .filter { it.totalSize() < 100_000 }
      .sumOf { it.totalSize() }
  }

  fun p2(input: String): Long {
    val commands: List<Command> = parseCommands(input)
    val root: Directory = commands.toDirectoryStructure()
    val allDirectories = root.allSubDirectoriesIncludingSelf()

    val diskSize = 70_000_000
    val updateSize = 30_000_000
    val usedSpace = root.totalSize()
    val freeSpace = diskSize - usedSpace
    val minDeleteSize = updateSize - freeSpace

    return allDirectories
      .filter { it.totalSize() >= minDeleteSize }
      .minBy { it.totalSize() }
      .totalSize()
  }

  private fun parseCommands(input: String): List<Command> {
    val commands: MutableList<Command> = mutableListOf()

    val iter = input.lines().filter { it.isNotEmpty() }.listIterator()
    iter.next()

    while (iter.hasNext()) {
      val line = iter.next()

      if (line.startsWith("$ cd")) {
        commands += Command.CD(line.substring("$ cd".length).trim())
      } else if (line == "$ ls") {
        val dirNames: MutableList<String> = mutableListOf()
        val files: MutableList<File> = mutableListOf()

        while (iter.hasNext()) {
          val entry = iter.next()
          if (entry.startsWith("$")) {
            iter.previous()
            break
          } else if (entry.startsWith("dir")) {
            dirNames += entry.substring("dir".length).trim()
          } else {
            val parts = entry.split(" ")
            files += File(
              name = parts[1].trim(),
              size = parts[0].toLong()
            )
          }
        }

        commands += Command.LS(dirNames, files)
      } else {
        error("Unexpected state")
      }
    }

    return commands
  }

  private fun List<Command>.toDirectoryStructure(): Directory {
    val root = Directory(name = "/", parent = null)
    var currentDir: Directory = root

    for (command in this) {
      when (command) {
        is Command.CD -> {
          currentDir = if (command.dirName == "..") {
            currentDir.parent!!
          } else {
            currentDir.getDirByName(command.dirName)
          }
        }
        is Command.LS -> {
          currentDir.files += command.files
          currentDir.directories += command.dirNames.map { Directory(name = it, parent = currentDir) }
        }
      }
    }

    return root
  }

  sealed class Command {
    data class CD(val dirName: String) : Command()
    data class LS(val dirNames: List<String>, val files: List<File>) : Command()
  }

  data class Directory(
    val name: String,
    val parent: Directory?,
    val directories: MutableList<Directory> = mutableListOf(),
    val files: MutableList<File> = mutableListOf()
  ) {
    fun getDirByName(name: String): Directory {
      return directories.first { it.name == name }
    }

    fun allSubDirectoriesIncludingSelf(): List<Directory> {
      return listOf(this) + directories.map { it.allSubDirectoriesIncludingSelf() }.flatten()
    }

    fun totalSize(): Long {
      return files.sumOf { it.size } + directories.sumOf { it.totalSize() }
    }

    override fun toString(): String {
      return name
    }
  }
  data class File(val name: String, val size: Long)
}
