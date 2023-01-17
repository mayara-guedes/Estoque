class Peca(
    val id: Int,
    var nomePeca: String,
    var quantidadePeca: Int
) {

    override fun toString(): String {
        return (
                """
            Id: $id | Nome: $nomePeca | Quantidade: $quantidadePeca
            """.trimIndent()
                )
    }

    fun atualizarCampos(nome: String, quantidade: Int) {
        this.nomePeca = nome
        this.quantidadePeca = quantidade
    }

}

fun main() {

    val menuPrincipal = """
     
    Escolha uma opção:
    
    1..............ADICIONAR ITEM
    2..............EDITAR ITEM
    3..............EXIBIR ITENS EM ESTOQUE
    4..............EXIBIR TODOS OS ITENS
    0..............FECHAR SISTEMA
""".trimIndent()

    var opcaoMenu: Int?
    var nome: String?
    var quantidade: Int?
    var pecas: MutableList<Peca> = mutableListOf()

    fun adicionarItem() {
        println("Digite o nome da peça: ")
        nome = readln().uppercase()
        println("Digite a quantidade de peças: ")
        quantidade = readln().toInt()

        pecas.add(Peca(pecas.size + 1, nome!!, quantidade!!))

    }

    fun editarItem() {

        println("Digite o id da peça que você deseja editar:")
        var editarId = readln().toInt()
        println("Digite o novo nome do item: ")
        var editarNome = readln().uppercase()
        println("Digite a quantidade do novo item : ")
        var qtdNovoItem = readln().toInt()

        if (qtdNovoItem!! <= 0 || qtdNovoItem!! > 999) throw LimiteEstoqueException()


        var itemEditado = pecas.find { it.id == editarId } ?: throw ItemInvalidoException()

        itemEditado.atualizarCampos(editarNome, qtdNovoItem)

        pecas[editarId - 1] = itemEditado

    }

    fun exibirItensEmEstoque() {
        println("*** ITENS EM ESTOQUE ***")
        for (i in 0 until pecas.size - 1) {
            var pecasEmEstoque = pecas.filter { it.quantidadePeca > 0 }
            println(" ${pecasEmEstoque[i]}")
        }

    }


    fun exibirTodosOsItens() {

        println("*** ITENS CADASTRADOS NO SISTEMA ***")
        println("  ID   |   Item    |   Quantidade ")
        for (i in 0 until pecas.size) {
            println(" ${pecas[i]}")
        }
    }

    do {

        println(menuPrincipal)
        opcaoMenu = readln().toInt()
        println(opcaoMenu)

        when (opcaoMenu) {
            1 -> adicionarItem()
            2 -> editarItem()
            3 -> exibirItensEmEstoque()
            4 -> exibirTodosOsItens()


        }
    } while (opcaoMenu != 0)
}


class ItemInvalidoException : Exception()

class LimiteEstoqueException : Exception()

