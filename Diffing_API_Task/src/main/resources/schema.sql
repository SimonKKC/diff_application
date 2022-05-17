/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  cheungkwaikwan
 * Created: 16-May-2022
 */


create table if not exists UserInput (
  id varchar(16) not null,
  type varchar(1) not null,
  content varchar(4000) not null
);

ALTER TABLE UserInput ADD PRIMARY KEY (id, type);


