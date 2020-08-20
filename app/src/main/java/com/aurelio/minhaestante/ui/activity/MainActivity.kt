package com.aurelio.minhaestante.ui.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.aurelio.minhaestante.R
import com.aurelio.minhaestante.databinding.ActivityMainBinding
import com.aurelio.minhaestante.di.MinhaEstanteApplication
import com.aurelio.minhaestante.di.ViewModelProviderFactory
import com.aurelio.minhaestante.domain.Label
import com.aurelio.minhaestante.domain.LabelColorEnum
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import javax.inject.Inject

class MainActivity : AppCompatActivity(), AddLabelDialog.Listener, LabelAdapter.LabelListener {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: LabelAdapter
    private val labelList = mutableListOf<Label>()

    private lateinit var dialog: AddLabelDialog

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory
    private val sharedViewModel: SharedViewModel by viewModels { viewModelProviderFactory }
    private val viewModel: MainViewModel by viewModels { viewModelProviderFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MinhaEstanteApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.readingFragment,
            R.id.shelfFragment
        ), drawer)

        findViewById<Toolbar>(R.id.toolbar).setupWithNavController(navController, appBarConfiguration)
        findViewById<BottomNavigationView>(R.id.bottom_navigation).setupWithNavController(navController)

        findViewById<FloatingActionButton>(R.id.addButton).setOnClickListener {
            val focus = currentFocus
            if (focus != null) {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(focus.windowToken, 0)
            }
            navController.navigate(R.id.addBookFragment)
        }

        adapter = LabelAdapter(this)

        val listView = findViewById<RecyclerView>(R.id.labels_list)
        listView.adapter = adapter
        dialog = AddLabelDialog(this, this, binding.root)
        viewModel.labels.observe(this, Observer {list ->
            val newList = mutableListOf(Label(name="Adicionar Rótulo", color = LabelColorEnum.NONE)).also {
                it.addAll(list)
            }
            adapter.submitList(newList)
        })
    }

    override fun onBackPressed() {
        val focus = currentFocus
        if (focus == null) {
            super.onBackPressed()
        } else {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(focus.windowToken, 0)
        }
    }

    override fun addLabel(label: Label) {
        viewModel.addLabel(label)
    }

    override fun onLabelClicked(position: Int) {
        if (position == 0) {
            dialog.show()
        }
        else {
            val item = labelList[position-1]
            sharedViewModel.changeChosenLabel(item)
        }
    }

    override fun onDeleteClicked(label: Label) {
        Toast.makeText(this, "Removing label ${label.name}", Toast.LENGTH_LONG).show()
    }
}
