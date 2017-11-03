package com.eure.traveling


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.eure.traveling.entity.Type


class MainActivity : AppCompatActivity() {

    private val sectionsPagerAdapter by lazy { SectionsPagerAdapter(supportFragmentManager) }
    private val viewPager by lazy { findViewById<ViewPager>(R.id.pager) }
    private val tabLayout by lazy { findViewById<TabLayout>(R.id.tab_layout) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager.adapter = sectionsPagerAdapter
        viewPager.offscreenPageLimit = Type.values().size
        tabLayout.setupWithViewPager(viewPager)
    }

    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return ShotListFragment.newInstance(Type.valueOf(position).name.toLowerCase())
        }

        /**
         * タブの数を決定
         */
        override fun getCount(): Int {
            // Show 6 total pages.
            return Type.values().size
        }

        /**
         * タブのタイトルを決定
         */
        override fun getPageTitle(position: Int): CharSequence {
            return Type.valueOf(position).name
        }
    }
}
