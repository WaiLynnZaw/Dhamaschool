package group.ripple.dhamaschool;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

public class NewsDao extends BaseDAO<NewsObj> {

	private String UNIQ_IDX = "id";
	private String TABLE = "tbl_news";

	public NewsDao(Context context, DatabaseManager dbm) {
		super(dbm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<NewsObj> getAll() {
		// TODO Auto-generated method stub
		ArrayList<NewsObj> objs = new ArrayList<NewsObj>();
		String query = "SELECT * FROM " + TABLE + " ORDER BY updated DESC";

		Cursor c = dbm.getDB().rawQuery(query, null);
		if (c.moveToFirst()) {
			do {
				NewsObj newsObj = new NewsObj();

				newsObj.setId(c.getString(0));
                newsObj.setUpdated(c.getString(1));
                newsObj.setContent(c.getString(2));


				objs.add(newsObj);

			} while (c.moveToNext());
		}
		return objs;
	}
	
	
	

	@Override
	public NewsObj getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

	@Override
	public long CRUD(NewsObj obj) {
		// TODO Auto-generated method stub
		if (!dbm.CheckIfExist2(TABLE, UNIQ_IDX, obj.getId() + ""))
			return insert(obj);
		else
			return update(obj);
	}

	@Override
	public long insert(NewsObj obj) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();

		cv.put("id", obj.getId());
		cv.put("updated", obj.getUpdated());
		cv.put("content", obj.getContent());


		return dbm.getDB().insert(TABLE, null, cv);
	}

	@Override
	public boolean delete(NewsObj obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long update(NewsObj obj) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
        cv.put("updated", obj.getUpdated());
        cv.put("content", obj.getContent());

		String filter = UNIQ_IDX + "='" + obj.getId() + "'";
		return dbm.getDB().update(TABLE, cv, filter, null);
	}

}
