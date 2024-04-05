package com.hotel.hotelapplication.domain.repository

// In repository only abstraction, no implementation.
//interface RoomRepository {
//    only fetch the room here
//    Either memiliki 2 value <kiri,kanan> / <error,success>.
//    value kiri merepresentasikan error case
//    value kanan merepresentasikan success case
//    jadi jika hasil sebuah function return mengembalikan nilai error,
    //    maka Either akan mengembalikan value error. dan sebaliknya
//    suspend fun getRoom(): Either<NetworkError,List<Room>>
//}