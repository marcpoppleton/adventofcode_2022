class Day7 : Day {

    private val tree:MutableMap<String,TreeNode> = mutableMapOf()

        private val MAX_SIZE = 100000
        private val REQUIRED_SPACE = 30000000
        private val DISK_SPACE = 70000000

    override fun part1(entries: List<String>): Long {
        parseData(entries)
        return tree
            .filter { (_,entry) -> entry.type==Type.FOLDER && entry.size<= MAX_SIZE }
            .values.fold(0L) { acc, treeNode ->  acc + treeNode.size }
    }

    override fun part2(entries: List<String>): Long {
        parseData(entries)
        val minFolderSize = REQUIRED_SPACE - (DISK_SPACE - tree["/"]!!.size)
        return  tree
            .filter { (_,entry) -> entry.type==Type.FOLDER && entry.size>=minFolderSize}
            .minBy { entry  -> entry.value.size }.value.size
    }

    private fun parseData(entries: List<String>){
        //init the tree with the root node
        var root = TreeNode("/",Type.FOLDER)
        tree["/"] = root
        var currentNode:TreeNode = root
        entries.forEach { line ->
            with(line){
                when{
                    startsWith("$ cd") -> currentNode = processCD(line,currentNode) //process cd
                    startsWith("$ ls") -> null //process ls
                    contains("dir") -> processDir(line,currentNode) //process dir type node
                    else -> processFile(line, currentNode) // process file type node
                }
            }
        }
    }

    private fun processCD(line:String, currentNode: TreeNode):TreeNode{
        val (folderName) = cdRegex.find(line)!!.destructured

        return when(folderName){
            ".." -> currentNode.parent ?: currentNode // process moving up, default to current
            else -> tree[currentNode.name + "/" + folderName] ?: currentNode // process getting folder, default to current
        }
    }

    private fun processDir(line:String,currentNode: TreeNode){
        val (folderName) = dirRegex.find(line)!!.destructured
        val absoluteName = currentNode.name + "/" + folderName
        val folder = TreeNode(absoluteName,Type.FOLDER)
        currentNode.addChild(folder)
        tree[absoluteName] = folder
    }

    private fun processFile(line:String,currentNode: TreeNode){
        val (fileSize,fileName) = fileRegex.find(line)!!.destructured
        val absoluteName = currentNode.name + "/" + fileName
        var file = TreeNode(absoluteName,Type.FILE)
        currentNode.addChild(file)
        file.setSize(fileSize.toLong())
        tree[absoluteName] = file
    }

    private val cdRegex = """cd (\S+)""".toRegex()
    private val dirRegex = """dir (\w+)""".toRegex()
    private val fileRegex = """(\d+) (\S+)""".toRegex()
}

class TreeNode(val name:String, val type:Type){
    var parent:TreeNode? = null
        private set
    var size:Long=0
        private set
    private var children:MutableList<TreeNode> = mutableListOf()

    fun addChild(child:TreeNode){
        child.parent = this
        children.add(child)
    }

    fun setSize(childSize:Long){
        size +=childSize
        parent?.setSize(childSize)
    }

    override fun toString(): String = "${type.name} $name ($size)"

}

enum class Type{
    FILE,
    FOLDER
}