package ru.job4j.generic;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RoleStoreTest {

    @Test
    void whenAddAndFindThenRolenameIsDriver() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Driver"));
        Role result = store.findById("1");
        assertThat(result.getRole()).isEqualTo("Driver");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("4", "singer"));
        Role rsl = store.findById("1");
        assertThat(rsl).isNull();
    }

    @Test
    void whenAddDuplicateAndFindRoleIsSinger() {
        RoleStore store = new RoleStore();
        store.add(new Role("4", "singer"));
        store.add(new Role("4", "driver"));
        Role rsl = new Role("4", "singer");
        assertThat(rsl.getRole()).isEqualTo("singer");
        rsl = store.findById("4");
        assertThat(rsl.getRole()).isEqualTo("singer");
    }

    @Test
    void whenReplaceThenRoleIsDeveloper() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "driver"));
        store.replace("1", new Role("1", "developer"));
        Role rsl = store.findById("1");
        assertThat(rsl.getRole()).isEqualTo("developer");
    }
    @Test
    void whenNoReplaceRoleThenNoChangeRole() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "driver"));
        store.replace("2", new Role("2", "developer"));
        Role rsl = store.findById("1");
        assertThat(rsl.getRole()).isEqualTo("driver");
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "driver"));
        store.delete("1");
        Role rsl = store.findById("1");
        assertThat(rsl).isNull();
    }

    @Test
    void whenNoDeleteUserThenRoleIsCEO() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "CEO"));
        store.delete("2");
        Role rsl = store.findById("1");
        assertThat(rsl.getRole()).isEqualTo("CEO");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "CEO"));
        store.replace("1", new Role("1", "new_CEO"));
        Role rsl = store.findById("1");
        assertThat(rsl.getRole()).isEqualTo("new_CEO");
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "CEO"));
        boolean rsl = store.replace("10", new Role("10", "bookkeeper"));
        assertThat(rsl).isFalse();
    }

    @Test
    void whenDeleteOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "janitor"));
        boolean rsl = store.delete("1");
        assertThat(rsl).isTrue();
    }

    @Test
    void whenDeleteNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "janitor"));
        boolean rsl = store.delete("2");
        assertThat(rsl).isFalse();
    }
}