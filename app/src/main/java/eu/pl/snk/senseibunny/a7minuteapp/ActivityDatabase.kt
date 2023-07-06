package eu.pl.snk.senseibunny.a7minuteapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ActivityEntity::class], version=1)
abstract class ActivityDatabase: RoomDatabase() {

    abstract fun activityDao():AcitivityDao

    companion object{
        @Volatile
        private  var INSTANCE: ActivityDatabase?=null

        fun getInstance(context: Context):ActivityDatabase{
            synchronized(this){
                var instance= INSTANCE

                if(instance==null){ //create instance to database only if it wasnt created already
                    instance= Room.databaseBuilder(
                        context.applicationContext,
                        ActivityDatabase::class.java,
                        "person_database"
                    ).fallbackToDestructiveMigration().build()

                    INSTANCE=instance
                }
                return instance
            }
        }
    }
}