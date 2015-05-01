package com.kancolle.server.model.kcsapi.member;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.annotation.JSONField;
import com.kancolle.server.dao.annotation.Column;
import com.kancolle.server.dao.annotation.Id;

public class MemberBasic {

	@JSONField(ordinal = 1)
	private String api_member_id;

	@JSONField(ordinal = 2)
	private String api_nickname;

	@JSONField(ordinal = 3)
	private String api_nickname_id;

	@JSONField(ordinal = 4)
	private int api_active_flag;

	@JSONField(ordinal = 5)
	private long api_starttime;

	@JSONField(ordinal = 6)
	private int api_level;

	@JSONField(ordinal = 7)
	private int api_rank;

	@JSONField(ordinal = 8)
	private long api_experience;

	@JSONField(ordinal = 9)
	private String api_fleetname;

	@JSONField(ordinal = 10)
	private String api_comment;

	@JSONField(ordinal = 11)
	private String api_comment_id;

	@JSONField(ordinal = 12)
	private int api_max_chara;

	@JSONField(ordinal = 13)
	private int api_max_slotitem;

	@JSONField(ordinal = 14)
	private int api_max_kagu;

	@JSONField(ordinal = 15)
	private long api_playtime;

	@JSONField(ordinal = 16)
	private int api_tutorial;

	@JSONField(ordinal = 17)
	private JSONArray api_furniture;

	@JSONField(ordinal = 18)
	private int api_count_deck;

	@JSONField(ordinal = 19)
	private int api_count_kdock;

	@JSONField(ordinal = 20)
	private int api_count_ndock;

	@JSONField(ordinal = 21)
	private int api_fcoin;

	@JSONField(ordinal = 22)
	private int api_st_win;

	@JSONField(ordinal = 23)
	private int api_st_lose;
	
	@JSONField(ordinal = 24)
	private int api_ms_count;

	@JSONField(ordinal = 25)
	private int api_ms_success;

	@JSONField(ordinal = 26)
	private int api_pt_win;

	@JSONField(ordinal = 27)
	private int api_pt_lose;

	@JSONField(ordinal = 28)
	private int api_pt_challenged;

	@JSONField(ordinal = 29)
	private int api_pt_challenged_win;

	@JSONField(ordinal = 30)
	private int api_firstflag;

	@JSONField(ordinal = 31)
	private int api_tutorial_progress;

	@JSONField(ordinal = 32)
	private JSONArray api_pvp;

	@JSONField(ordinal = 33)
	private int api_medals;

	public String getApi_member_id() {
		return api_member_id;
	}

	@Id(name = "member_id", type = String.class)
	public void setApi_member_id(String api_member_id) {
		this.api_member_id = api_member_id;
	}

	public String getApi_nickname() {
		return api_nickname;
	}

	@Column(name = "nickname", type = String.class)
	public void setApi_nickname(String api_nickname) {
		this.api_nickname = api_nickname;
	}

	public String getApi_nickname_id() {
		return api_nickname_id;
	}

	@Column(name = "nickname_id", type = String.class)
	public void setApi_nickname_id(String api_nickname_id) {
		this.api_nickname_id = api_nickname_id;
	}

	public int getApi_active_flag() {
		return api_active_flag;
	}

	@Column(name = "active_flag", type = int.class)
	public void setApi_active_flag(int api_active_flag) {
		this.api_active_flag = api_active_flag;
	}

	public long getApi_starttime() {
		return api_starttime;
	}

	@Column(name = "starttime", type = long.class)
	public void setApi_starttime(long api_starttime) {
		this.api_starttime = api_starttime;
	}

	public int getApi_level() {
		return api_level;
	}

	@Column(name = "level", type = int.class)
	public void setApi_level(int api_level) {
		this.api_level = api_level;
	}

	public int getApi_rank() {
		return api_rank;
	}

	@Column(name = "rank", type = int.class)
	public void setApi_rank(int api_rank) {
		this.api_rank = api_rank;
	}

	public long getApi_experience() {
		return api_experience;
	}

	@Column(name = "experience", type = long.class)
	public void setApi_experience(long api_experience) {
		this.api_experience = api_experience;
	}

	public String getApi_fleetname() {
		return api_fleetname;
	}

	@Column(name = "fleetname", type = String.class)
	public void setApi_fleetname(String api_fleetname) {
		this.api_fleetname = api_fleetname;
	}

	public String getApi_comment() {
		return api_comment;
	}

	@Column(name = "comment", type = String.class)
	public void setApi_comment(String api_comment) {
		this.api_comment = api_comment;
	}

	public String getApi_comment_id() {
		return api_comment_id;
	}

	@Column(name = "comment_id", type = String.class)
	public void setApi_comment_id(String api_comment_id) {
		this.api_comment_id = api_comment_id;
	}

	public int getApi_max_chara() {
		return api_max_chara;
	}

	@Column(name = "max_chara", type = int.class)
	public void setApi_max_chara(int api_max_chara) {
		this.api_max_chara = api_max_chara;
	}

	public int getApi_max_slotitem() {
		return api_max_slotitem;
	}

	@Column(name = "max_slotitem", type = int.class)
	public void setApi_max_slotitem(int api_max_slotitem) {
		this.api_max_slotitem = api_max_slotitem;
	}

	public int getApi_max_kagu() {
		return api_max_kagu;
	}

	@Column(name = "max_kagu", type = int.class)
	public void setApi_max_kagu(int api_max_kagu) {
		this.api_max_kagu = api_max_kagu;
	}

	public long getApi_playtime() {
		return api_playtime;
	}

	@Column(name = "playtime", type = long.class)
	public void setApi_playtime(long api_playtime) {
		this.api_playtime = api_playtime;
	}

	public long getApi_tutorial() {
		return api_tutorial;
	}

	@Column(name = "tutorial", type = int.class)
	public void setApi_tutorial(int api_tutorial) {
		this.api_tutorial = api_tutorial;
	}

	public JSONArray getApi_furniture() {
		return api_furniture;
	}

	@Column(name = "furniture", type = String.class)
	public void setApi_furniture(String api_furniture) {
		this.api_furniture = JSON.parseArray(api_furniture);
	}

	public int getApi_count_deck() {
		return api_count_deck;
	}

	@Column(name = "count_deck", type = int.class)
	public void setApi_count_deck(int api_count_deck) {
		this.api_count_deck = api_count_deck;
	}

	public int getApi_count_kdock() {
		return api_count_kdock;
	}

	@Column(name = "count_kdock", type = int.class)
	public void setApi_count_kdock(int api_count_kdock) {
		this.api_count_kdock = api_count_kdock;
	}

	public int getApi_count_ndock() {
		return api_count_ndock;
	}

	@Column(name = "count_ndock", type = int.class)
	public void setApi_count_ndock(int api_count_ndock) {
		this.api_count_ndock = api_count_ndock;
	}

	public int getApi_fcoin() {
		return api_fcoin;
	}

	@Column(name = "fcoin", type = int.class)
	public void setApi_fcoin(int api_fcoin) {
		this.api_fcoin = api_fcoin;
	}

	public int getApi_st_win() {
		return api_st_win;
	}

	@Column(name = "st_win", type = int.class)
	public void setApi_st_win(int api_st_win) {
		this.api_st_win = api_st_win;
	}

	public int getApi_st_lose() {
		return api_st_lose;
	}

	@Column(name = "st_lose", type = int.class)
	public void setApi_st_lose(int api_st_lose) {
		this.api_st_lose = api_st_lose;
	}

	public int getApi_ms_count() {
		return api_ms_count;
	}

	@Column(name = "ms_count", type = int.class)
	public void setApi_ms_count(int api_ms_count) {
		this.api_ms_count = api_ms_count;
	}

	public int getApi_ms_success() {
		return api_ms_success;
	}

	@Column(name = "ms_success", type = int.class)
	public void setApi_ms_success(int api_ms_success) {
		this.api_ms_success = api_ms_success;
	}

	public int getApi_pt_win() {
		return api_pt_win;
	}

	@Column(name = "pt_win", type = int.class)
	public void setApi_pt_win(int api_pt_win) {
		this.api_pt_win = api_pt_win;
	}

	public int getApi_pt_lose() {
		return api_pt_lose;
	}

	@Column(name = "pt_lose", type = int.class)
	public void setApi_pt_lose(int api_pt_lose) {
		this.api_pt_lose = api_pt_lose;
	}

	public int getApi_pt_challenged() {
		return api_pt_challenged;
	}
	
	@Column(name = "pt_challenged", type = int.class)
	public void setApi_pt_challenged(int api_pt_challenged) {
		this.api_pt_challenged = api_pt_challenged;
	}

	public int getApi_pt_challenged_win() {
		return api_pt_challenged_win;
	}

	@Column(name = "pt_challenged_win", type = int.class)
	public void setApi_pt_challenged_win(int api_pt_challenged_win) {
		this.api_pt_challenged_win = api_pt_challenged_win;
	}

	public int getApi_firstflag() {
		return api_firstflag;
	}

	@Column(name = "firstflag", type = int.class)
	public void setApi_firstflag(int api_firstflag) {
		this.api_firstflag = api_firstflag;
	}

	public int getApi_tutorial_progress() {
		return api_tutorial_progress;
	}

	@Column(name = "tutorial_progress", type = int.class)
	public void setApi_tutorial_progress(int api_tutorial_progress) {
		this.api_tutorial_progress = api_tutorial_progress;
	}

	public JSONArray getApi_pvp() {
		return api_pvp;
	}

	@Column(name = "pvp", type = String.class)
	public void setApi_pvp(String api_pvp) {
		this.api_pvp = JSON.parseArray(api_pvp);
	}

	public int getApi_medals() {
		return api_medals;
	}

	@Column(name = "medals", type = int.class)
	public void setApi_medals(int api_medals) {
		this.api_medals = api_medals;
	}
}
