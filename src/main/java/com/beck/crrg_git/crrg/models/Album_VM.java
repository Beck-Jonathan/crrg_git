package com.beck.crrg_git.crrg.models;

public class Album_VM extends Album{
  private int album_size;

  public Album_VM(Album _album, int size){
    super(_album.getAlbum_ID(),_album.getAlbum_Name(),_album.getIs_Active());
    this.album_size=size;
  }
  public int getAlbum_size() {
    return album_size;
  }

  public void setAlbum_size(int album_size) {
    this.album_size = album_size;
  }



}
