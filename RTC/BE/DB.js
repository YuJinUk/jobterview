const mysql = require('mysql2');

// Create the connection pool. The pool-specific settings are the defaults
const pool = mysql.createPool
({
  host: 'localhost',
  port:'3306',
  user: 'ssafy',
  database: 'jobterview',
  password: 'ssafy',
  waitForConnections: true,
  connectionLimit: 10,
  queueLimit: 0
});

const setRooms = async (createdDate, maxMember, nowMember, roomName, roomPassword)=>
{
    const sql = 'INSERT INTO room (created_date,max_member,now_member,room_name, room_password) VALUES (?, ?,?,?,?);';
    const values = [createdDate, maxMember, nowMember, roomName, roomPassword];
    
    const serRoomsPool = pool.promise();
    const [rows] = await serRoomsPool.query(sql,values);
    //console.log(rows);
    return rows;
};



const joinRoom = async (roomName)=>
{   
    //console.log("here come")
    const sql = 'update  room set now_member =  now_member+1 where room_name = (?);';
    const values = [roomName];
    
    const joinRoomPool = pool.promise();
    const [rows] = await joinRoomPool.query(sql, values);
    //console.log(rows);
    return rows;
};

const outRoom = async (roomName)=>
{   
    //console.log("here come")
    const sql = 'update  room set now_member =  now_member-1 where room_name = (?);';
    const values = [roomName];
    
    const outRoomPool = pool.promise();
    const [rows] = await outRoomPool.query(sql, values);
    //console.log(rows);
    return rows;
};

const deleteRoom = async (roomName)=>
{   
    //console.log("here come")
    const sql = 'delete from room where room_name = (?);';
    const values = [roomName];
    
    const deleteRoomPool = pool.promise();
    const [rows] = await deleteRoomPool.query(sql, values);
    //console.log(rows);
    return rows;
};

module.exports = 
{
    setRooms,
    joinRoom,
    outRoom,
    deleteRoom
};