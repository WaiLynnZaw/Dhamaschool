package group.ripple.dhamaschool;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

public abstract class BaseDAO<E> {

	protected DatabaseManager dbm;
	protected Context mContext;
	
	@SuppressWarnings("static-access")
	public BaseDAO(DatabaseManager dbm){
		this.dbm = dbm;
		mContext = this.dbm.mContext;
	}
	
	public abstract ArrayList<E> getAll();
	public abstract E getById(String id);
	public abstract long CRUD(E obj);
	public abstract long insert(E obj);
	public abstract boolean delete(E obj);
	public abstract long update(E obj);
	
	public void catchLog(String log) {
		if(BuildConfig.DEBUG)Log.i(getClass().getSimpleName(), log);
	}
}
