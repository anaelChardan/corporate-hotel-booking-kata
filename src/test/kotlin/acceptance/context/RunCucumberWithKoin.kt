package acceptance.context

import cucumber.api.cli.Main
import org.koin.core.context.startKoin
import tool.ContainerFactory


class RunCucumberWithKoin {
    companion object {
        @JvmStatic
        fun main(vararg args: String) {

            val regularServices = ContainerFactory().create()

            startKoin{ modules(regularServices) }

            Main.main(args)
        }
    }

}